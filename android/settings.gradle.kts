pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://packages.jetbrains.team/maven/p/ki/maven") }
        maven { url = uri("https://packages.jetbrains.team/maven/p/grazi/grazie-platform-public") }
        maven { url = uri("https://raw.githubusercontent.com/NexaAI/core/main") }
    }
}

rootProject.name = "Llm Hub"
include(":app")

// 条件性包含 Asset Pack 模块
// 当构建完整 APK 时（-PexcludeAssetPacks 未指定），包含 Asset Pack 模块用于 AAB 构建
// 当指定 -PexcludeAssetPacks 时，排除 Asset Pack 模块（用于直接 APK 构建，资产将合并到主模块）
if (!project.hasProperty("excludeAssetPacks")) {
    include(":qnn_pack")
    include(":sd_pack")
    include(":nexa_npu_pack")
}