# ogTagParser
Android library to parse open graph tags (ogTags) from given URL.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<br>
<img src="https://raw.githubusercontent.com/anandwana001/ogTagParser/master/image/screenshot_og_tag_parser.png" width="250" height="500"/>

## Getting Started



##### Step 1. Add the dependency
```
dependencies {
    implementation 'com.akshay.ogtagparser:ogtagparser:0.1.0'
}
```

##### Step 2. Pass the URL and get the Data
```
OgTagParser().execute(URL_TO_PARSE, object: LinkViewCallback {

    override fun onBeforeLoading() {
        // show some loading animations
    }

    override fun onAfterLoading(linkSourceContent: LinkSourceContent) {

        /**
         * 1. linkSourceContent.ogTitle
         * 2. linkSourceContent.ogDescription
         * 3. linkSourceContent.ogUrl
         * 4. linkSourceContent.ogSiteName
         * 5. linkSourceContent.ogType
         * 6. linkSourceContent.images
         */

    }
})
```

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

## Contribute
Love to see the contribution and build this repo a more better version. So if you have any issues, new ideas about implementations then just raise issue and we are open for Pull Requests. Improve and make it happen.
See [Contributing Guidelines](CONTRIBUTING.md).

## License
[License](LICENSE)
