package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.MethodsUnderTest;

import java.util.List;
import java.util.Map;

import org.example.Connection.RelationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SocialGraphDiffblueTest {
    /**
     * Test {@link SocialGraph#addPerson(Person)}.
     * <ul>
     *   <li>When {@link Person} (default constructor).</li>
     *   <li>Then {@link SocialGraph} (default constructor) AllPeople size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#addPerson(Person)}
     */
    @Test
    @DisplayName("Test addPerson(Person); when Person (default constructor); then SocialGraph (default constructor) AllPeople size is one")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"void SocialGraph.addPerson(Person)"})
    void testAddPerson_whenPerson_thenSocialGraphAllPeopleSizeIsOne() {
        // Arrange
        SocialGraph socialGraph = new SocialGraph();

        // Act
        socialGraph.addPerson(new Person());

        // Assert
        assertEquals(1, socialGraph.getAllPeople().size());
        assertEquals(1, socialGraph.getPeopleMap().size());
    }

    /**
     * Test {@link SocialGraph#connect(String, String, RelationType)}.
     * <ul>
     *   <li>When {@code ACQUAINTANCE}.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#connect(String, String, RelationType)}
     */
    @Test
    @DisplayName("Test connect(String, String, RelationType); when 'ACQUAINTANCE'")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"boolean SocialGraph.connect(String, String, RelationType)"})
    void testConnect_whenAcquaintance() {
        // Arrange, Act and Assert
        assertFalse(new SocialGraph().connect("Id A", "Id B", RelationType.ACQUAINTANCE));
    }

    /**
     * Test {@link SocialGraph#connect(String, String, RelationType)}.
     * <ul>
     *   <li>When {@code COLLEAGUE}.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#connect(String, String, RelationType)}
     */
    @Test
    @DisplayName("Test connect(String, String, RelationType); when 'COLLEAGUE'")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"boolean SocialGraph.connect(String, String, RelationType)"})
    void testConnect_whenColleague() {
        // Arrange, Act and Assert
        assertFalse(new SocialGraph().connect("Id A", "Id B", RelationType.COLLEAGUE));
    }

    /**
     * Test {@link SocialGraph#connect(String, String, RelationType)}.
     * <ul>
     *   <li>When {@code FAMILY}.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#connect(String, String, RelationType)}
     */
    @Test
    @DisplayName("Test connect(String, String, RelationType); when 'FAMILY'")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"boolean SocialGraph.connect(String, String, RelationType)"})
    void testConnect_whenFamily() {
        // Arrange, Act and Assert
        assertFalse(new SocialGraph().connect("Id A", "Id B", RelationType.FAMILY));
    }

    /**
     * Test {@link SocialGraph#connect(String, String, RelationType)}.
     * <ul>
     *   <li>When {@code FOLLOWED}.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#connect(String, String, RelationType)}
     */
    @Test
    @DisplayName("Test connect(String, String, RelationType); when 'FOLLOWED'")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"boolean SocialGraph.connect(String, String, RelationType)"})
    void testConnect_whenFollowed() {
        // Arrange, Act and Assert
        assertFalse(new SocialGraph().connect("Id A", "Id B", RelationType.FOLLOWED));
    }

    /**
     * Test {@link SocialGraph#connect(String, String, RelationType)}.
     * <ul>
     *   <li>When {@code FRIEND}.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#connect(String, String, RelationType)}
     */
    @Test
    @DisplayName("Test connect(String, String, RelationType); when 'FRIEND'")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"boolean SocialGraph.connect(String, String, RelationType)"})
    void testConnect_whenFriend() {
        // Arrange, Act and Assert
        assertFalse(new SocialGraph().connect("Id A", "Id B", RelationType.FRIEND));
    }

    /**
     * Test {@link SocialGraph#findById(String)}.
     * <p>
     * Method under test: {@link SocialGraph#findById(String)}
     */
    @Test
    @DisplayName("Test findById(String)")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"Person SocialGraph.findById(String)"})
    void testFindById() {
        // Arrange, Act and Assert
        assertNull(new SocialGraph().findById("42"));
    }

    /**
     * Test {@link SocialGraph#findByName(String)}.
     * <ul>
     *   <li>Given {@link SocialGraph} (default constructor) addPerson {@link Person} (default constructor).</li>
     *   <li>Then {@link SocialGraph} (default constructor) PeopleMap size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByName(String)}
     */
    @Test
    @DisplayName("Test findByName(String); given SocialGraph (default constructor) addPerson Person (default constructor); then SocialGraph (default constructor) PeopleMap size is one")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByName(String)"})
    void testFindByName_givenSocialGraphAddPersonPerson_thenSocialGraphPeopleMapSizeIsOne() {
        // Arrange
        SocialGraph socialGraph = new SocialGraph();
        socialGraph.addPerson(new Person());

        // Act
        socialGraph.findByName("Name");

        // Assert
        assertEquals(1, socialGraph.getPeopleMap().size());
    }

    /**
     * Test {@link SocialGraph#findByName(String)}.
     * <ul>
     *   <li>Given {@link SocialGraph} (default constructor).</li>
     *   <li>When {@code Name}.</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByName(String)}
     */
    @Test
    @DisplayName("Test findByName(String); given SocialGraph (default constructor); when 'Name'; then return Empty")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByName(String)"})
    void testFindByName_givenSocialGraph_whenName_thenReturnEmpty() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().findByName("Name").isEmpty());
    }

    /**
     * Test {@link SocialGraph#findByName(String)}.
     * <ul>
     *   <li>When empty string.</li>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByName(String)}
     */
    @Test
    @DisplayName("Test findByName(String); when empty string; then return size is one")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByName(String)"})
    void testFindByName_whenEmptyString_thenReturnSizeIsOne() {
        // Arrange
        SocialGraph socialGraph = new SocialGraph();
        Person person = new Person();
        socialGraph.addPerson(person);

        // Act
        List<Person> actualFindByNameResult = socialGraph.findByName("");

        // Assert
        assertEquals(1, actualFindByNameResult.size());
        assertEquals(1, socialGraph.getPeopleMap().size());
        assertSame(person, actualFindByNameResult.get(0));
    }

    /**
     * Test {@link SocialGraph#findByCity(String)}.
     * <ul>
     *   <li>Given {@link Person} (default constructor) City is {@code London}.</li>
     *   <li>Then {@link SocialGraph} (default constructor) PeopleMap size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByCity(String)}
     */
    @Test
    @DisplayName("Test findByCity(String); given Person (default constructor) City is 'London'; then SocialGraph (default constructor) PeopleMap size is one")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByCity(String)"})
    void testFindByCity_givenPersonCityIsLondon_thenSocialGraphPeopleMapSizeIsOne() {
        // Arrange
        Person person = new Person();
        person.setCity("London");

        SocialGraph socialGraph = new SocialGraph();
        socialGraph.addPerson(person);

        // Act
        socialGraph.findByCity("Oxford");

        // Assert
        assertEquals(1, socialGraph.getPeopleMap().size());
    }

    /**
     * Test {@link SocialGraph#findByCity(String)}.
     * <ul>
     *   <li>Given {@link Person} (default constructor) City is {@code Oxford}.</li>
     *   <li>Then return size is one.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByCity(String)}
     */
    @Test
    @DisplayName("Test findByCity(String); given Person (default constructor) City is 'Oxford'; then return size is one")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByCity(String)"})
    void testFindByCity_givenPersonCityIsOxford_thenReturnSizeIsOne() {
        // Arrange
        Person person = new Person();
        person.setCity("Oxford");

        SocialGraph socialGraph = new SocialGraph();
        socialGraph.addPerson(person);

        // Act
        List<Person> actualFindByCityResult = socialGraph.findByCity("Oxford");

        // Assert
        assertEquals(1, actualFindByCityResult.size());
        assertEquals(1, socialGraph.getPeopleMap().size());
        assertSame(person, actualFindByCityResult.get(0));
    }

    /**
     * Test {@link SocialGraph#findByCity(String)}.
     * <ul>
     *   <li>Given {@link SocialGraph} (default constructor).</li>
     *   <li>Then return Empty.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#findByCity(String)}
     */
    @Test
    @DisplayName("Test findByCity(String); given SocialGraph (default constructor); then return Empty")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.findByCity(String)"})
    void testFindByCity_givenSocialGraph_thenReturnEmpty() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().findByCity("Oxford").isEmpty());
    }

    /**
     * Test {@link SocialGraph#getDirectConnections(String)}.
     * <p>
     * Method under test: {@link SocialGraph#getDirectConnections(String)}
     */
    @Test
    @DisplayName("Test getDirectConnections(String)")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.getDirectConnections(String)"})
    void testGetDirectConnections() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getDirectConnections("42").isEmpty());
    }

    /**
     * Test {@link SocialGraph#shortestPath(String, String)}.
     * <p>
     * Method under test: {@link SocialGraph#shortestPath(String, String)}
     */
    @Test
    @DisplayName("Test shortestPath(String, String)")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.shortestPath(String, String)"})
    void testShortestPath() {
        // Arrange, Act and Assert
        assertNull(new SocialGraph().shortestPath("42", "42"));
    }

    /**
     * Test {@link SocialGraph#getPeopleWithinDegrees(String, int)}.
     * <ul>
     *   <li>When three.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#getPeopleWithinDegrees(String, int)}
     */
    @Test
    @DisplayName("Test getPeopleWithinDegrees(String, int); when three")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"Map SocialGraph.getPeopleWithinDegrees(String, int)"})
    void testGetPeopleWithinDegrees_whenThree() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getPeopleWithinDegrees("42", 3).isEmpty());
    }

    /**
     * Test {@link SocialGraph#getPeopleWithinDegrees(String, int)}.
     * <ul>
     *   <li>When zero.</li>
     * </ul>
     * <p>
     * Method under test: {@link SocialGraph#getPeopleWithinDegrees(String, int)}
     */
    @Test
    @DisplayName("Test getPeopleWithinDegrees(String, int); when zero")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"Map SocialGraph.getPeopleWithinDegrees(String, int)"})
    void testGetPeopleWithinDegrees_whenZero() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getPeopleWithinDegrees("42", 0).isEmpty());
    }

    /**
     * Test {@link SocialGraph#getMutualConnections(String, String)}.
     * <p>
     * Method under test: {@link SocialGraph#getMutualConnections(String, String)}
     */
    @Test
    @DisplayName("Test getMutualConnections(String, String)")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.getMutualConnections(String, String)"})
    void testGetMutualConnections() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getMutualConnections("Id A", "Id B").isEmpty());
    }

    /**
     * Test {@link SocialGraph#getSuggestions(String)}.
     * <p>
     * Method under test: {@link SocialGraph#getSuggestions(String)}
     */
    @Test
    @DisplayName("Test getSuggestions(String)")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.getSuggestions(String)"})
    void testGetSuggestions() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getSuggestions("42").isEmpty());
    }

    /**
     * Test {@link SocialGraph#getAllPeople()}.
     * <p>
     * Method under test: {@link SocialGraph#getAllPeople()}
     */
    @Test
    @DisplayName("Test getAllPeople()")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"java.util.Collection SocialGraph.getAllPeople()"})
    void testGetAllPeople() {
        // Arrange, Act and Assert
        assertTrue(new SocialGraph().getAllPeople().isEmpty());
    }

    /**
     * Test getters and setters.
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link SocialGraph#getAllConnections()}
     *   <li>{@link SocialGraph#getPeopleMap()}
     * </ul>
     */
    @Test
    @DisplayName("Test getters and setters")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"List SocialGraph.getAllConnections()", "Map SocialGraph.getPeopleMap()"})
    void testGettersAndSetters() {
        // Arrange
        SocialGraph socialGraph = new SocialGraph();

        // Act
        List<Connection> actualAllConnections = socialGraph.getAllConnections();
        Map<String, Person> actualPeopleMap = socialGraph.getPeopleMap();

        // Assert
        assertTrue(actualAllConnections.isEmpty());
        assertTrue(actualPeopleMap.isEmpty());
    }

    /**
     * Test {@link SocialGraph#totalPeople()}.
     * <p>
     * Method under test: {@link SocialGraph#totalPeople()}
     */
    @Test
    @DisplayName("Test totalPeople()")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"int SocialGraph.totalPeople()"})
    void testTotalPeople() {
        // Arrange, Act and Assert
        assertEquals(0, new SocialGraph().totalPeople());
    }

    /**
     * Test {@link SocialGraph#totalConnections()}.
     * <p>
     * Method under test: {@link SocialGraph#totalConnections()}
     */
    @Test
    @DisplayName("Test totalConnections()")
    @Tag("MaintainedByDiffblue")
    @MethodsUnderTest({"int SocialGraph.totalConnections()"})
    void testTotalConnections() {
        // Arrange, Act and Assert
        assertEquals(0, new SocialGraph().totalConnections());
    }
}
