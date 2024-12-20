/**
 * To test with JUnit, add JUnit to your project. To do this, go to
 * Project->Properties. Select "Java Build Path". Select the "Libraries"
 * tab and "Add Library". Select JUnit, then JUnit 4.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
  @Test
  public void test() {
    defaultJudgeTest();
    customJudgeTest();
    empties();
    singletons();
    // your tests go here
    //mismatchAlignment();
    //gapIntroduction();
    longSequences();
    repeatedCharacterSequences();
    testEmptyAndNonEmptyAlignment();

    alignmentAgainstAllGaps();
    zeroLengthSequences();
    testCorrectAlignment();

  }

  @Test
  public void testCorrectAlignment() {
    String x = "ACGTTAC";
    String y = "AGTTC";

    String expectedAlignedX = "ACGTTAC";
    String expectedAlignedY = "A_GTT_C";

    SequenceAligner sa = new SequenceAligner(x, y);

    assertTrue(sa.isAligned(), "aligned.");
    assertEquals(expectedAlignedX, sa.getAlignedX(), "X sequence is correct.");
    assertEquals(expectedAlignedY, sa.getAlignedY(), "Y sequence is correct.");
  }


  @Test
  public void alignmentAgainstAllGaps() {
    SequenceAligner sa = new SequenceAligner("AAAA", "____"); // Assuming you handle direct gap inputs
    assertTrue(sa.isAligned(), "Sequences should be considered aligned.");
    assertEquals(-4, sa.getScore(), "Score should reflect four gaps."); // Assuming -1 per gap
  }

  @Test
  public void zeroLengthSequences() {
    SequenceAligner sa = new SequenceAligner("", "");
    assertTrue(sa.isAligned(), "Sequences should be considered aligned.");
    assertEquals(0, sa.getScore(), "Score should be zero for two empty sequences.");
  }


  @Test
  public void defaultJudgeTest() {
    Judge judge = new Judge();
    assertEquals(2, judge.score('A',  'A'));
    assertEquals(2, judge.score("A",  "A"));
  }

  @Test
  public void customJudgeTest() {
    Judge judge = new Judge(3, -3, -2);
    assertEquals(3, judge.score('A',  'A'));
    assertEquals(3, judge.score("A",  "A"));
  }

  /**********************************************
   * Testing SequenceAligner.fillCache()
   **********************************************/
  @Test
  public void empties() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("", "");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
  }

  @Test
  public void singletons() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("A", "A");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(1, 0);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(1, 1);
    assertNotNull(result);
    assertEquals(2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
  }

  /**********************************************
   * Testing SequenceAligner.traceback()
   **********************************************/
  @Test
  public void simpleAlignment() {
    SequenceAligner sa;
    sa = new SequenceAligner("ACGT", "ACGT");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("ACGT", sa.getAlignedY());
  }




//  @Test
//  public void mismatchAlignment() {
//    SequenceAligner sa = new SequenceAligner("ACGT", "TGCA");
//    assertTrue(sa.isAligned());
//    assertEquals(-8, sa.getScore()); // Assuming mismatch score is -2 and there are 4 mismatches
//  }

//  @Test
//  public void gapIntroduction() {
//    SequenceAligner sa = new SequenceAligner("ACGT", "ACG");
//    assertTrue(sa.isAligned());
//    assertEquals(6, sa.getScore()); // Assuming 3 matches with +2 each and one gap with -1
//  }

  @Test
  public void longSequences() {
    SequenceAligner sa = new SequenceAligner("ACGTACGTACGT", "ACGTACGTACGT");
    assertTrue(sa.isAligned());
    assertEquals(24, sa.getScore()); // Assuming 12 matches with +2 each
  }

  @Test
  public void repeatedCharacterSequences() {
    SequenceAligner sa = new SequenceAligner("AAAA", "AAA");
    assertTrue(sa.isAligned());
    assertEquals(5, sa.getScore()); // Assuming 3 matches with +2 each and one gap with -1
  }


  @Test
  public void testEmptyAndNonEmptyAlignment() {
    // Test with the first sequence empty and the second non-empty
    SequenceAligner sa1 = new SequenceAligner("", "ACGT");
    assertTrue(sa1.isAligned(), "Sequences should be considered aligned.");
    assertEquals(-4, sa1.getScore(), "Score should account for four gaps.");
    assertEquals("____", sa1.getAlignedX(), "Aligned X should be four gaps.");
    assertEquals("ACGT", sa1.getAlignedY(), "Aligned Y should match the non-empty sequence.");

    // Test with the second sequence empty and the first non-empty
    SequenceAligner sa2 = new SequenceAligner("ACGT", "");
    assertTrue(sa2.isAligned(), "Sequences should be considered aligned.");
    assertEquals(-4, sa2.getScore(), "Score should account for four gaps.");
    assertEquals("ACGT", sa2.getAlignedX(), "Aligned X should match the non-empty sequence.");
    assertEquals("____", sa2.getAlignedY(), "Aligned Y should be four gaps.");
  }
}

