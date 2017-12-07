package com.batch.escalog;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Tests the log lines output by LogFmt
 * @author Guillaume PERRUDIN
 */
public class LogFmtTest
{

    private LayoutProducer layoutProducer;

    private LogFmt logger;


    @Before
    public void init()
    {
        LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();

        ctx.reset();

        this.layoutProducer = new LayoutProducer();
        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setLayout(layoutProducer);
        appender.setContext(ctx);
        appender.start();

        Logger basicLogger = ctx.getLogger(LogFmtTest.class);
        basicLogger.addAppender(appender);
        this.logger = LogFmt.from(basicLogger);

        ctx.start();
    }

    @Test
    public void logOutputTest()
    {
        logger.with("key1", "value1").info("here is the message");
        String line = getEndLog(layoutProducer.consume());
        assertEquals("msg=\"here is the message\" key1=value1\n", line);

        logger.with("key with spaces", "value with spaces").info("message with sequences : \n \r \\");
        line = getEndLog(layoutProducer.consume());
        assertEquals("msg=\"message with sequences : \\n \\r \\\\\" key with spaces=\"value with spaces\"\n", line);

        logger.with("key1", "value1").and("keyInt", 143).and("keyBool", true).info("message-without-spaces");
        line = getEndLog(layoutProducer.consume());
        assertEquals("msg=message-without-spaces key1=value1 keyInt=143 keyBool=true\n", line);

    }

    /**
     * Gets the end of the end log (from msg=)
     */
    private static String getEndLog(String log)
    {
        return log.substring(log.lastIndexOf(" msg=")+1, log.length());
    }


    /**
     * Layout that allows to consume log lines.
     */
    public class LayoutProducer extends LogFmtLayout
    {

        Queue<String> queue = new LinkedList<>();


        @Override
        public String doLayout(ILoggingEvent iLoggingEvent)
        {
            queue.offer(super.doLayout(iLoggingEvent));
            // no output
            return "";
        }

        public String consume()
        {
            return queue.poll();
        }

    }


}
