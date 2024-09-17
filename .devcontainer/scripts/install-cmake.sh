#!/usr/bin/env bash
#-------------------------------------------------------------------------------------------------------------
# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License. See https://go.microsoft.com/fwlink/?linkid=2090316 for license information.
#-------------------------------------------------------------------------------------------------------------

set -e

echo "Installing apt packages..."
apt-get update
export DEBIAN_FRONTEND=noninteractive
apt-get -y install build-essential cmake cppcheck valgrind clang lldb llvm gdb
apt-get autoremove -y
apt-get clean -y
rm -rf /var/lib/apt/lists/*

echo "Installing vcpkg..."
export VCPKG_ROOT=/usr/local/vcpkg
export VCPKG_DOWNLOADS=/usr/local/vcpkg-downloads
export PATH="${PATH}:${VCPKG_ROOT}"
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
"${DIR}/install-vcpkg.sh" vscode