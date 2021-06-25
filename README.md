# ogTagParser
Android library to parse open graph tags (ogTags) from given URL.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<br>
<img src="https://raw.githubusercontent.com/anandwana001/ogTagParser/master/art/screenshot_og_tag_parser_test_release.png" width=230 height=440 />

## Getting Started

##### Step 1. Add the dependency
Root Level Build.gradle file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

module level build.gradle file
```gradle
dependencies {
    ...
    implementation 'com.github.anandwana001:ogTagParser:1.0.2'
}
```

##### Step 2. Pass the URL and get the Data
```kotlin
OgTagParser().getContents(

    URL_TO_PARSE,

    object : LinkViewCallback {

        override fun onAfterLoading(linkSourceContent: LinkSourceContent) {

             // og:title
             linkSourceContent.ogTitle

             // og:description
             linkSourceContent.ogDescription

             // og:url
             linkSourceContent.ogUrl

             // og:site_name
             linkSourceContent.ogSiteName

             // og:type
             linkSourceContent.ogType

             // og:image
             linkSourceContent.images
        }
    }
)
```

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

## Contribute
Love to see the contribution and build this repo a more better version. So if you have any issues, new ideas about implementations then just raise issue and we are open for Pull Requests. Improve and make it happen.
See [Contributing Guidelines](CONTRIBUTING.md).

## License
[License](LICENSE.md)
