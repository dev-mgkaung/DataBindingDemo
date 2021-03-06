# DataBindingDemo
Android’s Data Binding with MVVM pattern (Kotlin lanaguage , support androidx)


[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.3.61-blue.svg)](https://kotlinlang.org)

Introduction
--------
Are you looking for databinding  sample source code?
(You can learn my code. In my project. I 'd written by Databinding with MVVM pattern.)

Project Structure
--------
<ol>
  <li>Step 1. Data fetch from server (Mock api) ** Using OkHttp and Retrofit 2 </li>
<li>Step 2. Response data  save in local database ** Using Room Persistence Library </li>
<li>Step 3. Offline Data show with Recyclerview Loading partial data   ** Using Paging Library </li>
<li>Step 4. Swith Theme - Dark Night   ** Using AppCompactDelegate </li>
<li>Step 5. Project Framework using MVVM Pattern with Databinding </li>
</ol>
Screenshoot
--------
  <img alt="English Unicdoe Choose" src="https://github.com/dev-mgkaung/DataBindingDemo/blob/master/screenshot/databinding_cover.jpg" />

Rest Api (MockApi)
--------
WHAT IS MOCK API?
MockAPI is a simple tool that lets you easily mock up APIs, generate custom data, and preform operations on it using RESTful interface. MockAPI is meant to be used as a prototyping/testing/learning tool.

This is free mock api website https://mockapi.io/

### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Android Data Binding][data-binding]
* [Coroutines][coroutines] for Coroutines
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [GSON][gson] for json convert
* [Room Persistence Library][room] for Offline Data 
* [Paging][paging] for bounded data source
* [MVVM architecture][mvvm] for clean Architecture



[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[data-binding]: https://developer.android.com/topic/libraries/data-binding/index.html
[mvvm]: https://blog.mindorks.com/mvvm-architecture-android-tutorial-for-beginners-step-by-step-guide
[coroutines]: https://github.com/Kotlin/kotlinx.coroutines
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[gson]: https://github.com/google/gson
[room]: https://developer.android.com/topic/libraries/architecture/room
[paging]: https://developer.android.com/topic/libraries/architecture/paging
App Screenshoot
--------

   <img alt="English Zawgyi Choose" src="https://github.com/dev-mgkaung/DataBindingDemo/blob/master/screenshot/screenshot_one.jpg" width="250"/> <img alt="English Zawgyi Choose" src="https://github.com/dev-mgkaung/DataBindingDemo/blob/master/screenshot/screenshot_two.jpg" width="250"/>



License
--------

    Copyright 2019 dev-mgkaung

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
