#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<EOF
Usage: $(basename "$0") [options]

Optional:
  -o, --output     APK output directory (default: ./build)
  --verbose        Print detailed build information

Examples:
  $(basename "$0")
  $(basename "$0") --output ~/Desktop/apk --verbose
EOF
}

log() {
  if [[ "${VERBOSE}" == "true" ]]; then
    echo "$@"
  fi
}

VARIANT="release"
OUTPUT_DIR=""
VERBOSE="false"

while [[ $# -gt 0 ]]; do
  case "$1" in
    -o|--output) OUTPUT_DIR="$2"; shift 2;;
    --verbose) VERBOSE="true"; shift;;
    -h|--help) usage; exit 0;;
    *) echo "Error: Unknown argument: $1"; usage; exit 1;;
  esac
done

case "${VARIANT}" in
  debug|release);;
  *) echo "Error: Invalid variant: ${VARIANT}"; usage; exit 1;;
esac

REPOSITORY_DIR="$(git rev-parse --show-toplevel 2>/dev/null || pwd)"

if [[ -z "${OUTPUT_DIR}" ]]; then
  OUTPUT_DIR="${REPOSITORY_DIR}/build"
fi
mkdir -p "${OUTPUT_DIR}"

ASSEMBLE_TASK="assemble$(tr '[:lower:]' '[:upper:]' <<< "${VARIANT:0:1}")${VARIANT:1}"

log "Variant: ${VARIANT}"
log "Task: ${ASSEMBLE_TASK}"
log "Output: ${OUTPUT_DIR}"

test -x "${REPOSITORY_DIR}/gradlew" || chmod +x "${REPOSITORY_DIR}/gradlew"

log "Cleaning..."
"${REPOSITORY_DIR}/gradlew" :app:clean --stacktrace
log "Cleaned."

log "Building: ${ASSEMBLE_TASK}"
"${REPOSITORY_DIR}/gradlew" ":app:${ASSEMBLE_TASK}" --stacktrace

APK_FILE="${REPOSITORY_DIR}/app/build/outputs/apk/${VARIANT}/app-${VARIANT}.apk"

if [[ -z "${APK_FILE}" || ! -f "${APK_FILE}" ]]; then
  echo "Error: APK not found: ${APK_FILE}"
  exit 1
else
  cp "${APK_FILE}" "${OUTPUT_DIR}/"
  log "Exported: ${OUTPUT_DIR}/$(basename "${APK_FILE}")"
fi
