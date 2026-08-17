package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MathUtilsDiffblueTest {
    /**
     * Test {@link MathUtils#addTwoAndThree()}.
     * <p>
     * Method under test: {@link MathUtils#addTwoAndThree()}
     */
    @Test
    @DisplayName("Test addTwoAndThree()")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"int MathUtils.addTwoAndThree()"})
    void testAddTwoAndThree() {
        // Arrange, Act and Assert
        assertEquals(5, new MathUtils().addTwoAndThree());
    }
}
