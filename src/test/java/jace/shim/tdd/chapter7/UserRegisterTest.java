package jace.shim.tdd.chapter7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockWeakPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();;
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockWeakPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        BDDMockito.given(mockWeakPasswordChecker.checkPasswordWeak("pw")).willReturn(true);
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
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();

        assertEquals("email@email.com", realEmail);
    }
}