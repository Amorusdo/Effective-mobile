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

rootProject.name = "Test task  for Effective Mobile"
include(":app")


include(":core:core-ui")
include(":core:core-network")
include(":core:core-database")
include(":core:core-utils")

include(":feature:auth:auth-ui")
include(":feature:auth:auth-domain")
include(":feature:auth:auth-api")

include(":feature:courses:courses-api")
include(":feature:courses:courses-domain")
include(":feature:courses:courses-ui")


include(":feature:favorites:favorites-ui")
include(":feature:favorites:favorites-domain")

include(":feature:account:account-ui")