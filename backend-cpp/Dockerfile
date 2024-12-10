FROM ubuntu:22.04

# Install Clang
RUN apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install --assume-yes \
        curl \
        gpg \
        software-properties-common \
    && mkdir --parents /usr/share/keyrings/ \
    && curl --fail --location --show-error --silent https://apt.llvm.org/llvm-snapshot.gpg.key \
        | gpg --dearmor - \
        | tee /usr/share/keyrings/llvm-toolchain-16.gpg > /dev/null \
    && echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/llvm-toolchain-16.gpg] http://apt.llvm.org/$(lsb_release -cs)/ llvm-toolchain-$(lsb_release -cs)-16 main \n \
    deb-src [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/llvm-toolchain-16.gpg] http://apt.llvm.org/$(lsb_release -cs)/ llvm-toolchain-$(lsb_release -cs)-16 main" \
        >> /etc/apt/sources.list.d/llvm-toolchain-16.list \
    && apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install --assume-yes \
        clang-16 \
        clang-format-16 \
        clang-tidy-16 \
        clangd-16 \
        lldb-16 \
    && rm -rf /var/lib/apt/lists/* \
    && update-alternatives --install /usr/local/bin/clang clang /usr/bin/clang-16 99 \
    && update-alternatives --install /usr/local/bin/clang-format clang-format /usr/bin/clang-format-16 99 \
    && update-alternatives --install /usr/local/bin/clang-tidy clang-tidy /usr/bin/clang-tidy-16 99 \
    && update-alternatives --install /usr/local/bin/clang++ clang++ /usr/bin/clang++-16 99 \
    && update-alternatives --install /usr/local/bin/clangd clangd /usr/bin/clangd-16 99 \
    && update-alternatives --install /usr/local/bin/lldb lldb /usr/bin/lldb-16 99

# Install CMake
RUN apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install --assume-yes --no-install-recommends gpg lsb-release \
    && curl --fail --location --show-error --silent https://apt.kitware.com/keys/kitware-archive-latest.asc 2>/dev/null \
        | gpg --dearmor - \
        | tee /usr/share/keyrings/kitware-archive-keyring.gpg >/dev/null \
    && echo "deb [signed-by=/usr/share/keyrings/kitware-archive-keyring.gpg] https://apt.kitware.com/ubuntu/ $(lsb_release -cs) main" \
        | tee /etc/apt/sources.list.d/kitware.list >/dev/null \
    && apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install --assume-yes \
        cmake \
        kitware-archive-keyring

# Developer tools
RUN apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install --no-install-recommends --assume-yes \
        curl \
        file \
        gdb \
        git \
        libstdc++-12-dev \
        ninja-build \
        parallel \
        pkg-config \
        psmisc \
        ripgrep \
        tree \
        unzip \
        vim \
        zip \
    && rm -rf /var/lib/apt/lists/*

# Clone vcpkg
RUN mkdir /root/src \
    && git clone https://github.com/microsoft/vcpkg /root/src/vcpkg
ENV VCPKG_ROOT /root/src/vcpkg
RUN git config --add --system safe.directory /root/src/vcpkg

# To improve the quality of life of people using dev containers
RUN cp /etc/skel/.bashrc "/root/.bashrc"

COPY lldbinit /root/.lldbinit
