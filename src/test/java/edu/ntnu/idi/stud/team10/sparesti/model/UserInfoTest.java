package edu.ntnu.idi.stud.team10.sparesti.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.stud.team10.sparesti.enums.OccupationStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserInfoTest {

  private UserInfo userInfo;
  private final Long testId = 1L;
  private final User testUser = new User();
  private final String testFirstName = "John";
  private final String testLastName = "Doe";
  private final LocalDate testDateOfBirth = LocalDate.of(1990, 1, 1);
  private final OccupationStatus testOccupationStatus = OccupationStatus.EMPLOYED;
  private final Integer testMotivation = 4;
  private final Integer testIncome = 50000;
  private Set<BudgetingProduct> testBudgetingProducts;
  private final List<String> testBudgetingLocations = new ArrayList<>();

  @BeforeEach
  public void setUp() {
    userInfo = new UserInfo();
    userInfo.setId(testId);
    userInfo.setUser(testUser);
    userInfo.setFirstName(testFirstName);
    userInfo.setLastName(testLastName);
    userInfo.setDateOfBirth(testDateOfBirth);
    userInfo.setOccupationStatus(testOccupationStatus);
    userInfo.setMotivation(testMotivation);
    userInfo.setIncome(testIncome);
    testBudgetingProducts = new HashSet<>();
    userInfo.setBudgetingProducts(testBudgetingProducts);
    userInfo.setBudgetingLocations(testBudgetingLocations);
  }

  @Test
  public void testUserInfoAttributes() {
    assertNotNull(userInfo);
    assertEquals(testId, userInfo.getId());
    assertEquals(testUser, userInfo.getUser());
    assertEquals(testFirstName, userInfo.getFirstName());
    assertEquals(testLastName, userInfo.getLastName());
    assertEquals(testDateOfBirth, userInfo.getDateOfBirth());
    assertEquals(testOccupationStatus, userInfo.getOccupationStatus());
    assertEquals(testMotivation, userInfo.getMotivation());
    assertEquals(testIncome, userInfo.getIncome());
    assertEquals(testBudgetingProducts, userInfo.getBudgetingProducts());
    assertEquals(testBudgetingLocations, userInfo.getBudgetingLocations());
  }

  @Test
  public void testEqualsAndHashCode() {
    UserInfo userInfo1 =
        new UserInfo(
            testId,
            testUser,
            testFirstName,
            testLastName,
            testDateOfBirth,
            testOccupationStatus,
            testMotivation,
            testIncome,
            testBudgetingProducts,
            testBudgetingLocations);
    UserInfo userInfo2 =
        new UserInfo(
            testId,
            testUser,
            testFirstName,
            testLastName,
            testDateOfBirth,
            testOccupationStatus,
            testMotivation,
            testIncome,
            testBudgetingProducts,
            testBudgetingLocations);
    UserInfo userInfo3 =
        new UserInfo(
            2L,
            new User(),
            "Jane",
            "Doe",
            LocalDate.of(1995, 5, 5),
            OccupationStatus.UNEMPLOYED,
            3,
            30000,
            new HashSet<>(),
            new ArrayList<>());

    assertEquals(userInfo1, userInfo2);
    assertNotEquals(userInfo1, userInfo3);
    assertEquals(userInfo1.hashCode(), userInfo2.hashCode());
    assertNotEquals(userInfo1.hashCode(), userInfo3.hashCode());
  }

  @Test
  public void testToString() {
    String expectedToString =
        "UserInfo{id=1, user="
            + testUser
            + ", firstName='John', lastName='Doe', dateOfBirth=1990-01-01, occupationStatus=EMPLOYED, motivation=4, income=50000}";
    assertEquals(expectedToString, userInfo.toString());
  }

  @Test
  public void testConstructor() {
    assertEquals(testId, userInfo.getId());
    assertEquals(testUser, userInfo.getUser());
    assertEquals(testFirstName, userInfo.getFirstName());
    assertEquals(testLastName, userInfo.getLastName());
    assertEquals(testDateOfBirth, userInfo.getDateOfBirth());
    assertEquals(testOccupationStatus, userInfo.getOccupationStatus());
    assertEquals(testMotivation, userInfo.getMotivation());
    assertEquals(testIncome, userInfo.getIncome());
    assertEquals(testBudgetingProducts, userInfo.getBudgetingProducts());
    assertEquals(testBudgetingLocations, userInfo.getBudgetingLocations());
  }
}
