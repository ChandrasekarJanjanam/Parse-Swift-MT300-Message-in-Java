# Parse-Swift-MT300-Message-in-Java
SWIFT or Society for Worldwide Interbank Financial Telecommunication provides a network to allow financial and non-financial institutions (e.g. corporates) to transfer financial transactions through a 'financial message'.
SWIFT messages consist of five blocks of the data including three headers, message content, and a trailer. Message types are crucial to identifying content.
All SWIFT messages include the literal "MT" (Message Type). This is followed by a three-digit number that denotes the message category, group and type. Consider the following example, which is an order to buy or sell via a third party:
Example: MT304

Ref Link : https://en.wikipedia.org/wiki/SWIFT_message_types#SWIFT_MT

Below jar files are required to parse Swift MT300 Message format

1.pw-swift-core-SRU2014-7.6.jar

2.js-1.7R2.jar

3.commons-lang-2.6.jar

SwiftMT300Parser - is main file for parsing logic

