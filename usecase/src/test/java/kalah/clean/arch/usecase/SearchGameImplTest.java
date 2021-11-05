package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.base.GameDtoTestBuilder;
import kalah.clean.arch.usecase.base.GameTestBuilder;
import kalah.clean.arch.usecase.gateway.GameDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * Unit tests for {@link SearchGameImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class SearchGameImplTest {

    private static final String GAME_ID = "gameId";

    @Mock
    private GameRepository repository;

    @Mock
    private GameBuilder builder;

    @InjectMocks
    private SearchGameImpl searchGame;

    @Test
    void searchGameIsSuccessfullyDone() {
        // given
        var game = GameTestBuilder.aStartedGame().build();
        given(repository.findById(GAME_ID)).willReturn(Optional.of(game));

        // and
        GameDto dto = GameDtoTestBuilder.aDto().build();
        given(builder.buildDto(game)).willReturn(dto);

        // when
        var actual = searchGame.search(GAME_ID);

        // then
        assertEquals(dto, actual);
    }

    @Test
    void searchGameFailsBecauseGameIsNotFound() {
        // given
        given(repository.findById(GAME_ID)).willReturn(Optional.empty());

        // when
        // then
        assertThrows(RuntimeException.class, () -> searchGame.search(GAME_ID));
    }
}