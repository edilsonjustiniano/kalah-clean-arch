package kalah.clean.arch.usecase;

import kalah.clean.arch.domain.entity.Game;
import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.base.GameTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link DeleteGameImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class DeleteGameImplTest {

    private static final String GAME_ID = "gameId";

    @Mock
    private GameRepository repository;

    @InjectMocks
    private DeleteGameImpl deleteGame;

    @Test
    void deleteAGameIsSuccessfullyDone() {
        // given
        var game = GameTestBuilder.aStartedGame().build();
        given(repository.findById(GAME_ID)).willReturn(Optional.of(game));
        doNothing().when(repository).delete(game);

        // when
        deleteGame.delete(GAME_ID);

        // then
        verify(repository).delete(game);
    }

    @Test
    void deleteAGameIsNotDoneBecauseGameIsNotFound() {
        // given
        given(repository.findById(GAME_ID)).willReturn(Optional.empty());

        // when
        // then
        Assertions.assertThrows(RuntimeException.class, () -> deleteGame.delete(GAME_ID));
    }
}