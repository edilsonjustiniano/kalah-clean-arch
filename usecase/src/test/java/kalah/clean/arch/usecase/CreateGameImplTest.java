package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.base.GameDtoTestBuilder;
import kalah.clean.arch.usecase.base.GameTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Unit tests for {@link CreateGameImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class CreateGameImplTest {

    @Mock
    private GameRepository repository;

    @Mock
    private GameBuilder builder;

    @InjectMocks
    private CreateGameImpl createGame;

    @Test
    void startANewGameIsSuccessfullyDone() {
        // given
        var game = GameTestBuilder.aStartedGame().build();
        given(builder.buildGame()).willReturn(game);
        given(repository.save(game)).willReturn(game);

        var dto = GameDtoTestBuilder.aDto().build();
        given(builder.buildDto(game)).willReturn(dto);

        // when
        var actual = createGame.start();

        // then
        assertEquals(dto, actual);
    }
}