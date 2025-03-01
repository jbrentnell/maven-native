package org.codehaus.mojo.natives.c;

import java.io.File;
import org.codehaus.mojo.natives.compiler.CompilerConfiguration;
import static org.codehaus.mojo.natives.test.TestUtils.*;
import org.codehaus.plexus.PlexusTestCase;
import org.codehaus.plexus.util.cli.Commandline;
import static org.junit.Assert.*;


public class CCompilerTest
    extends PlexusTestCase
{
    private CCompiler compiler;

    private CompilerConfiguration config;

    private static File sourceFile = new File( "source.c" );

    private static File objectFile = new File( "object.o" );

    private static String[] simpleArgv = { "-o", "object.o", "-c", "source.c" };

    public void setUp()
        throws Exception
    {
        super.setUp();

        this.compiler = new CCompiler();
        this.config = new CompilerConfiguration();
    }

    public void testSimpleCompilation()
    {
        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", simpleArgv[0], simpleArgv[1], simpleArgv[2], simpleArgv[3] };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );

    }

    public void testNonDefaultExecutable()
    {
        this.config.setExecutable( "cc" );
        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );
        String[] expected = new String[] { "cc", simpleArgv[0], simpleArgv[1], simpleArgv[2], simpleArgv[3] };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );
    }

    public void testStartOptions()
    {
        String[] startOptions = { "-s1", "-s2" };
        config.setStartOptions( startOptions );

        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", "-s1", "-s2", simpleArgv[0], simpleArgv[1], simpleArgv[2], simpleArgv[3] };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );
    }

    public void testIncludePaths()
    {
        File[] includePaths = { new File( "p1" ), new File( "p2" ) };

        config.setIncludePaths( includePaths );

        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", "-Ip1", "-Ip2", simpleArgv[0], simpleArgv[1], simpleArgv[2], simpleArgv[3] };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );
    }

    public void testSystemIncludePaths()
    {
        File[] includePaths = { new File( "p1" ), new File( "p2" ) };

        File[] systemIncludePaths = { new File( "sp1" ), new File( "sp2" ) };

        config.setIncludePaths( includePaths );

        config.setSystemIncludePaths( systemIncludePaths );

        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", "-Ip1", "-Ip2", "-Isp1", "-Isp2", simpleArgv[0], simpleArgv[1],
                simpleArgv[2], simpleArgv[3] } ;
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );
    }

    public void testMiddleOptions()
    {
        File[] includePaths = { new File( "p1" ), new File( "p2" ) };
        config.setIncludePaths( includePaths );

        String[] startOptions = { "-s1", "-s2" };
        String[] middleOptions = { "-m1", "-m2" };
        config.setStartOptions( startOptions );
        config.setMiddleOptions( middleOptions );

        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", "-s1", "-s2", "-Ip1", "-Ip2", "-m1", "-m2", simpleArgv[0],
            simpleArgv[1], simpleArgv[2], simpleArgv[3] };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );

    }

    public void testEndOptions()
    {
        File[] includePaths = { new File( "p1" ), new File( "p2" ) };
        config.setIncludePaths( includePaths );

        String[] startOptions = { "-s1", "-s2" };
        String[] middleOptions = { "-m1", "-m2" };
        String[] endOptions = { "-e1", "-e2" };
        config.setStartOptions( startOptions );
        config.setMiddleOptions( middleOptions );
        config.setEndOptions( endOptions );

        Commandline cl = compiler.getCommandLine( sourceFile, objectFile, config );

        String[] expected = new String[] { "gcc", "-s1", "-s2", "-Ip1", "-Ip2", "-m1", "-m2", simpleArgv[0],
                simpleArgv[1], simpleArgv[2], simpleArgv[3], "-e1", "-e2" };
        assertArrayEquals( formPlatformCommandline( expected ), cl.getCommandline() );
    }
}
