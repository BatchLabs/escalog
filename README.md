# Escalog

Allows to log in logfmt format with logback. See https://brandur.org/logfmt for more information about logfmt.

It provides :
- A logback layout that format 
- A slf4j compliant class that allows to use key-value pairs in your logs 

It uses markers (org.slf4j.Marker) to define key-value pairs so it can seamlessly work with slf4j.

## Installation

```xml
        <dependency>
            <groupId>com.batch.escalog</groupId>
            <artifactId>logging</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

To log using escalog, use **LogFmtLayout** in your logback.xml.
Here is an example which outputs to stdout in logfmt format :
```xml
<configuration>

    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="com.batch.escalog.LogFmtLayout">
                <AppName>My Application Name</AppName>  <!-- optional. sets the key-value : 'app="MyApplication Name"' -->
                <Prefix>My prefix</AppName>             <!-- optional. Prepends all log lines with the given value -->
            <layout/>
        </encoder>
    </appender>

</configuration>
```

## Usage


### Using LogFmt logger

The logger **LogFmt** is built on top of slf4j Logger, it provides all of its methods and additional logfmt specific methods.
Here is an example of how you can use it :
```java
LogFmt logger = LogFmt.from(LoggerFactory.getLogger(LogFmtLayoutTest.class));

logger.with("key1", "value1").and("key2", "value2").info("Everything is {}", "ok");
// time="2017-12-06T14:12:17" level=info package=com.batch.escalog module=LogFmtTest thread=main msg="Everything is ok" key1=value1 key2=value2
```

### Using slf4j logger
 
The same can also be done with slf4j Logger using **LogFmtMarker** :
```java
LogFmt logger = LoggerFactory.getLogger(LogFmtLayoutTest.class);

logger.info(LogFmtMarker.with("key1", "value1").and("key2", "value2") ,"Everything is {}", "ok");
// time="2017-12-06T14:12:17" level=info package=com.batch.escalog module=LogFmtTest thread=main msg="Everything is ok" key1=value1 key2=value2
```

### Using MDC

```java
LogFmt logger = LoggerFactory.getLogger(LogFmtLayoutTest.class);

MDC.put("key3", "value3");
logger.info(LogFmtMarker.with("key1", "value1").and("key2", "value2") ,"Everything is {}", "ok");
// time="2017-12-06T14:12:17" level=info package=com.batch.escalog module=LogFmtTest thread=main msg="Everything is ok"  key3=value3 key1=value1 key2=value2
```


## Native key-value pairs


Here is the list of native key-value pairs that are automatically added.

| Key | Description |
| --- | --- |
| `time`| date of the log (RFC3339 format) |
| `level`| log level : *error,warning,info,debug,trace,all* |
| `msg`| given message |
| `app`| app name (if provided) |
| `thread`| thread name |
| `error`| exception (if provided) : *ex: logger.error(new Exception("error"), "Something went wrong")* |


## TODO

- [ ] Configuration : enable or disable key-values (native, MDC, additional key-values); specify order and fields format (time, exception, etc)
