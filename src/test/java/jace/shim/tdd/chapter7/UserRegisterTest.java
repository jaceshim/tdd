package jace.shim.tdd.chapter7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();;
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true);
        assertThrows(WeakPasswordException.class, () -> {
           userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("중복 id가 존재하면 가입 실패")
    void checkDuplicateId() {
        fakeRepository.save(new User("id", "pw", "email@email.com"));
        assertThrows(DuplicateIdException.class, () -> {
           userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("회원가입이 성공하면 가입이메일로 메일을 전송")
    void whenRegisterUserThenSendEmail() {
        userRegister.register("id", "pw", "email@email.com");
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getSentEmail());
    }
}