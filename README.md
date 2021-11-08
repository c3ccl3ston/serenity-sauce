# serenity-saucelabs

[Serenity](http://www.thucydides.info/docs/serenity/) Integration with Sauce Labs.

<img src="https://training.saucelabs.com/images/logo.png" height = "100">

<img src="http://www.thucydides.info/docs/serenity/images/serenity-logo.png" height = "100">

## Setup
* Clone the repo
* Install dependencies `mvn install`
* You can setup environment variables for all sample repos (see Notes) or update `serenity.properties` file with your Sauce Labs Username and Access Key

## Running your tests
- To run parallel tests, run `mvn verify`
- Parallel tests will run against the number of browser/OS combos in your properties files, as long as you have a corresponding number of TestSuiteRunners

## Notes
* You can view your test results on the [Sauce Labs dashboard](https://app.saucelabs.com/dashboard/tests/vdc)
* To test on a different set of browsers, check out our [platform configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/)
* You can export the environment variables for the Username and Access Key of your Sauce Labs account
  
  ```
  export SAUCE_USERNAME=<sauce-username> &&
  export SAUCE_ACCESS_KEY=<sauce-access-key>
  ```