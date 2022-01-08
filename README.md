# ogTagParser
Kotlin library to parse open graph meta tags (ogTags) from given URL.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<br>
<img src="https://raw.githubusercontent.com/anandwana001/ogTagParser/master/art/screenshot_og_tag_parser_test_release.png" width=230 height=440 />

## Getting Started

<b>NOTE: This library is not hosted anywhere as of now.</b>

Pass the URL and get the Data
```kotlin
val content = OgTagParser().getContents(URL_TO_PARSE)
content?.let {
  val title = content.ogTitle,
  val description = content.ogDescription,
  val url = content.ogUrl,
  val site_name = content.ogSiteName,
  val type = content.ogType,
  val image = content.image
)
```

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

## Contribute
Love to see the contribution and build this repo a more better version. So if you have any issues, new ideas about implementations then just raise issue and we are open for Pull Requests. Improve and make it happen.
See [Contributing Guidelines](CONTRIBUTING.md).

## License
[License](LICENSE.md)
