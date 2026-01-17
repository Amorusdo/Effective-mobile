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

rootProject.name = "Test task for Effective Mobile"
include(":app")


include(":core:core")
include(":core:core-network")
include(":core:core-database")
include(":core:core-utils")
include(":core:core-navigation")
include(":core:core-ui")

include(":feature:auth")
include(":feature:favorites")
include(":feature:account")
include(":feature:courses")
include(":core:core:common")
include(":core:common")
include(":feature:navigation")
include(":core:core-domain")
include(":core:core-data")
include(":feature:di")
