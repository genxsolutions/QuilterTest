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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Quilter Test"
include(
    ":app",
    ":core:common",
    ":core:data",
    ":core:database",
    ":core:designsystem",
    ":core:domain",
    ":core:model",
    ":core:network",
    ":core:testing",
    ":feature:books"
)
 