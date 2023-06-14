# SeetaFace6-JNI

参考：

https://github.com/seetafaceengine/SeetaFace6

https://github.com/SeetaFace6Open/SeetaFace6JNI

本项目采用 Android Stduio cmake 编译。

生成的 so 文件位于 /app/build/intermediates/cmake/

想要生成不同架构的修改 build.gradle：

```groovy
defaultConfig {
    ...
    externalNativeBuild {
        cmake {
            abiFilters "arm64-v8a"
        }
    }

    ndk {
        abiFilters "arm64-v8a"
    }
}
```

