# Quick start

## Windows

The code will not build on Windows.
Do not try to modify the code so that it builds.
Instead, use the [Development Container](#development-container) provided.
You could also use [Windows Subsystem for Linux (WSL)](https://learn.microsoft.com/en-us/windows/wsl/about).

## Development Container

We provide a [Development Container (devcontainer)](https://containers.dev/) as a (mostly) reproducible build environment.
Once inside the container, you can build and run the programs with

```bash
cd /workspace
cmake -D CMAKE_BUILD_TYPE=Debug -B /builds/build -S backend-cpp
cmake --build /builds/build
ctest --test-dir /builds/build

(cd /builds/build && ./BackendCpp)

# In another terminal. This should also works outside the container.
curl http://localhost:8080/suggestions?q=Montr
```

## *nix

On Linux and macOS,

```bash
# if you don't have microsoft/vcpkg
git clone https://github.com/microsoft/vcpkg
export VCPKG_ROOT="$PWD/vcpkg"

cmake -D CMAKE_BUILD_TYPE=Debug -G Ninja -B build
cmake --build build
ctest --test-dir build

(cd build && ./BackendCpp)

# In another terminal
curl http://localhost:8080/suggestions?q=Montr
```

# Requirements

If you do not use the [Development Container](#development-container), you'll need

- Clang >= 16
- CMake >= 3.21
- Potentially the package `pkg-config`.
    If you need it and it's not already installed, an error message should tell you so explicitly.
