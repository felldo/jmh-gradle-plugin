/*
 * Copyright 2014-2021 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

rootProject.name = "jmh-gradle-plugin"

plugins {
    id("com.gradle.enterprise") version "3.12.4"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"

        publishAlways()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal() // for dependency on the shadow plugin
    }
}

File(rootDir, "samples").listFiles()?.forEach {
    val sampleName = it.name
    it.listFiles()?.forEach { lang ->
        includeBuild(lang) {
            name = "${sampleName}-${lang.name}"
        }
    }
}