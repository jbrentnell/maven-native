package org.codehaus.mojo.natives.plugin;

import static org.junit.jupiter.api.Assertions.*;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;
import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenPredefinedRepository;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;


@MavenJupiterExtension
class NativePluginIT {

  /**
   * Test that the compile phase will work properly. 
   * Tests that the appropriate .a and .inczip files are produced
   * 
   * @param result - result from the Maven execution
   */
  @MavenTest
  @MavenGoal("install")
  void compile_test(MavenExecutionResult result) {
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libpoint.a").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libpoint.inczip").isRegularFile();
  }

  /**
   * Test that the dependent inczip and library are downloaded.
   * Checks that the inczip file is expanded properly.
   * Checks that the new library and inczip file are produced.
   * 
   * @param result - result from the Maven execution
   */
  @MavenTest
  @MavenGoal("install")
  @MavenPredefinedRepository
  void inczip_test(MavenExecutionResult result) {
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .has("target/native/include");
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("native/include/Point.hh").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("lib/libpoint.a").isRegularFile();
    
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libcircle.a").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libcircle.inczip").isRegularFile();
  }

  /**
   * Test that the transitive dependent inczip and library are downloaded.
   * Checks that the inczip files are expanded properly.
   * Checks that the new library and inczip file are produced.
   * 
   * @param result - result from the Maven execution
   */
  @MavenTest
  @MavenGoal("package")
  @MavenPredefinedRepository
  void transitive_inczip_test(MavenExecutionResult result) {
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .has("target/native/include");
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("native/include/Point.hh").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("native/include/Circle.hh").isRegularFile();

    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("lib/libpoint.a").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("lib/libcircle.a").isRegularFile();

    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libtesttransitiveincludes.a").isRegularFile();
    assertThat(result).isSuccessful()
        .project().hasTarget()
        .withFile("libtesttransitiveincludes.inczip").isRegularFile();
  }

}
