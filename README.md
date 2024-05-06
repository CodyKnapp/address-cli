# Empora Assessment

## Instructions
### Build

### Run
* Create a config.properties file in the root of the project with the following contents:
```
AUTH_ID=your_auth_id
AUTH_TOKEN=your_auth_token
```

### Thought Process
The main file should do very little so that we can isolate and test units more easily... to that end, I have decided the
main file will only open the file and send that handle off to a processing class.

I considered using a tool like Spring Batch to accomplish this but decided against it since it would certainly be
overkill for a simple task like this.

There should be a single class that connects to the Smarty API for verifying the addresses.

