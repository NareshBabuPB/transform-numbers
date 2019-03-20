# Number To Words Convertor

Program to convert a given number to words in [short scale](https://en.wikipedia.org/wiki/Long_and_short_scales) format.

A work sample to try out AWS Lambda function.


### The project can be used in two ways:

#### 1. As a Standalone REPL:

Run the [DriverProgram](transform-numbers/src/main/java/DriverProgram.java), which will prompt for an input number to convert. Any non numerical input value will exit the program.

#### 2. As a Lambda function:

Project can be packaged and deployed as an AWS Lambda function. [ConversionRequestHandler class](transform-numbers/src/main/java/com/naresh/learning/handler/ConversionRequestHandler.java) acts as the handler class for the lambda function. 

For instructions on how to setup a lambda function see the [Getting started with AWS Lambda guide](https://docs.aws.amazon.com/lambda/latest/dg/getting-started-create-function.html).

[transform-numbers-api](https://github.com/NareshBabuPB/transform-numbers-api) is a terraform config project to setup this program as a lambda function and expose it through REST API.


### Build and Package:

Project can be packaged using maven's lifecycle targets.
```
mvn clean package
```
