import com.floward.Configuration

///*
// * Designed and developed by 2022 mohamedebrahim96 (mohamedebrahim96)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
    namespace  = "com.floward"

    compileSdk = Configuration.compileSdk
    defaultConfig {
        applicationId = "com.floward"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "com.floward.AppTestRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    hilt {
        enableAggregatingTask = true
    }

    kotlin {
        sourceSets.configureEach {
            kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }
}

dependencies {

    // modules
    implementation(project(":core-data"))

    // androidx
    implementation(libs.material)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.startup)

    // data binding
    implementation(libs.bindables)

    // di
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compiler)

    // coroutines
    implementation(libs.coroutines)

    // whatIf
    implementation(libs.whatif)

    // image loading
    implementation(libs.glide)
    implementation(libs.glide.palette)

    // bundler
    implementation(libs.bundler)

    // transformation animation
    implementation(libs.transformationLayout)

    // recyclerView
    implementation(libs.recyclerview)
    implementation(libs.baseAdapter)

    // custom views
    implementation(libs.rainbow)
    implementation(libs.androidRibbon)
    implementation(libs.progressView)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.android.test.runner)
}
