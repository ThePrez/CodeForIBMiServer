package com.github.theprez.codefori;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;

import com.github.theprez.codefori.Tracer.Dest;
import com.github.theprez.jcmdutils.StringUtils;

public class CodeForiServer {
    public static void main(final String[] _args) {

        final LinkedList<String> args = new LinkedList<String>();
        args.addAll(Arrays.asList(_args));
        if (args.remove("--version")) {
            System.out.println("Version: " + Version.s_version);
            System.out.println("Build time: " + Version.s_compileDateTime);
            System.exit(0);
        }

        if (args.remove("--server")) {
            
        }

        try {
            final SystemConnection conn = new SystemConnection();
            String testFile = System.getProperty("test.file", "");
            boolean testMode = StringUtils.isNonEmpty(testFile);
            if (testMode) {
                System.setIn(new FileInputStream(testFile));
            }
            final DataStreamProcessor io = new DataStreamProcessor(System.in, System.out, conn, testMode);

            io.run();
        } catch (final Exception e) {
            Tracer.err(e);
        }
        Tracer.warn("data stream processing completed (end of request stream?)");
        System.err.println("bye");
        System.exit(12);
    }
}