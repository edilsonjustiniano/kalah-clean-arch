package kalah.clean.arch.usecase;

import kalah.clean.arch.repository.gateway.GameRepository;
import kalah.clean.arch.usecase.base.GameDtoTestBuilder;
import kalah.clean.arch.usecase.base.GameTestBuilder;
import kalah.clean.arch.usecase.exception.GameException;
import kalah.clean.arch.usecase.gateway.GameDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static kalah.clean.arch.domain.entity.Game.Player.PLAYER_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

/**
 * Unit tests for {@link SearchGameImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class MovePitImplTest {

    private static final String GAME_ID = "gameId";
    private static final int PLAYER_ONE_FIRST_PIT = 1;
    private static final int PLAYER_TWO_FIRST_PIT = 8;

    @Mock
    private GameRepository repository;

    @Mock
    private GameBuilder builder;

    @Mock
    private GameDataValidator validator;

    @InjectMocks
    private MovePitImpl movePit;

    @Test
    void playerOneMoveAPitSuccessfully() throws GameException {
        // given
        var game = GameTestBuilder.aStartedGame().build();
        int normalizedPit = PLAYER_ONE_FIRST_PIT - 1;

        // and
        given(repository.findById(GAME_ID)).willReturn(Optional.of(game));
        doNothing().when(validator).validateMovement(game, normalizedPit);

        // and
        var dto = GameDtoTestBuilder.aDto().build();
        given(builder.buildDto(game)).willReturn(dto);
        // when
        GameDto actual = movePit.play(GAME_ID, PLAYER_ONE_FIRST_PIT);

        // then
        assertEquals(dto, actual);
    }

    @Test
    void playerTwoMoveAPitSuccessfully() throws GameException {
        // given
        var game = GameTestBuilder.aStartedGame().nextPlayer(PLAYER_TWO).build();
        int normalizedPit = PLAYER_TWO_FIRST_PIT - 1;

        // and
        given(repository.findById(GAME_ID)).willReturn(Optional.of(game));
        doNothing().when(validator).validateMovement(game, normalizedPit);

        // and
        var dto = GameDtoTestBuilder.aDto().build();
        given(builder.buildDto(game)).willReturn(dto);
        // when
        GameDto actual = movePit.play(GAME_ID, PLAYER_TWO_FIRST_PIT);

        // then
        assertEquals(dto, actual);
    }

    @Test
    void playerOneMoveAPitAndWinTheGame() throws GameException {
        // given
        var game = GameTestBuilder.aFinishedPlayerOneWinnerGame().build();
        int normalizedPit = PLAYER_ONE_FIRST_PIT - 1;

        // and
        given(repository.findById(GAME_ID)).willReturn(Optional.of(game));
        doNothing().when(validator).validateMovement(game, normalizedPit);

        // and
        var dto = GameDtoTestBuilder.aDto().build();
        given(builder.buildDto(game)).willReturn(dto);
        // when
        GameDto actual = movePit.play(GAME_ID, PLAYER_ONE_FIRST_PIT);

        // then
        assertEquals(dto, actual);
    }
}