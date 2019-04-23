package pt.caires.marsrover.multimedia.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_ID1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_VIDEO;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_UNKNOWN_ID;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.caires.marsrover.common.error.MarsRoverApiException;
import pt.caires.marsrover.config.MarsRoverProperties;
import pt.caires.marsrover.multimedia.MultimediaTestHelper;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.repo.MultimediaRepository;


@ExtendWith(MockitoExtension.class)
class MultimediaServiceTest
{
    @Mock
    private MultimediaRepository multimediaRepository;

    @Mock
    private MarsRoverProperties marsRoverProperties;

    private MultimediaService multimediaService;

    @BeforeEach
    void setUp()
    {
        this.multimediaService = new DefaultMultimediaService(multimediaRepository, marsRoverProperties);
    }

    // get multimedia - with data
    @Test
    void givenExistentMultimedia_whenGetMultimedia_thenReturnAllMultimedia()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_VIDEO, MOCK_TITLE2);
        final List<MRMultimedia> expected = Arrays.asList(mockMultimedia1, mockMultimedia2);
        when(multimediaRepository.findAll()).thenReturn(expected);

        // when
        final List<MRMultimedia> result = multimediaService.getMultimediaCollection();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(mockMultimedia1, mockMultimedia2));
        verify(multimediaRepository, times(1)).findAll();
        verifyNoMoreInteractions(multimediaRepository);
    }

    // get multimedia - with no data
    @Test
    void givenNoMultimedia_whenGetMultimedia_thenReturnEmptyCollection()
    {
        // given
        when(multimediaRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<MRMultimedia> result = multimediaService.getMultimediaCollection();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(multimediaRepository, times(1)).findAll();
        verifyNoMoreInteractions(multimediaRepository);
    }

    // create multimedia - ok
    @Test
    void givenValidParameter_whenCreateMultimedia_thenReturnMultimediaCreated()
    {
        // given
        final CreateMultimediaParameter mockParameter = MultimediaTestHelper.generateCreateMultimediaParameter(MOCK_TYPE_PHOTO);
        final MRMultimedia expectedMultimedia = MultimediaTestHelper.generateMultimedia(mockParameter.getType(), MOCK_TITLE1);
        when(multimediaRepository.save(any(MRMultimedia.class))).thenReturn(expectedMultimedia);

        // when
        final MRMultimedia result = multimediaService.createMultimedia(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(expectedMultimedia.getId()));
        assertThat(result.getType(), is(expectedMultimedia.getType()));
        assertThat(result.getTitle(), is(expectedMultimedia.getTitle()));
        verify(multimediaRepository, times(1)).save(any(MRMultimedia.class));
        verifyNoMoreInteractions(multimediaRepository);
    }

    // updateTile - ok
    @Test
    void givenValidParameters_whenUpdateMultimediaTitle_thenReturnUpdatedMultimedia()
    {
        // given
        final long mockMultimediaId = MOCK_ID1;
        final String mockNewTitle = MOCK_TITLE2;
        final UpdateMultimediaParameter mockParameter = MultimediaTestHelper.generateUpdateMultimediaParameter(mockMultimediaId, mockNewTitle);
        final MRMultimedia targetMultimedia = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia expectedMultimedia = MultimediaTestHelper.generateMultimedia(targetMultimedia.getId(),
            targetMultimedia.getType(),
            mockNewTitle,
            targetMultimedia.getDateCreated());
        when(multimediaRepository.findById(anyLong())).thenReturn(Optional.of(targetMultimedia));
        when(multimediaRepository.save(any(MRMultimedia.class))).thenReturn(expectedMultimedia);

        // when
        final MRMultimedia result = multimediaService.updateMultimediaTitle(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(targetMultimedia.getId()));
        assertThat(result.getType(), is(targetMultimedia.getType()));
        assertThat(result.getTitle(), is(mockNewTitle));
        verify(multimediaRepository, times(1)).findById(mockMultimediaId);
        verify(multimediaRepository, times(1)).save(any(MRMultimedia.class));
        verifyNoMoreInteractions(multimediaRepository);
    }

    // updateTile - nok (multimedia id not found)
    @Test
    void givenUnknownMultimediaId_whenUpdateMultimediaTitle_thenThrowSpecificException()
    {
        // given
        final long mockMultimediaId = MOCK_UNKNOWN_ID;
        final UpdateMultimediaParameter mockParameter = MultimediaTestHelper.generateUpdateMultimediaParameter(mockMultimediaId, MOCK_TITLE2);
        when(multimediaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when + then
        assertThrows(MarsRoverApiException.class, () -> multimediaService.updateMultimediaTitle(mockParameter));
        verify(multimediaRepository, times(1)).findById(mockMultimediaId);
        verify(multimediaRepository, times(0)).save(any(MRMultimedia.class));
        verifyNoMoreInteractions(multimediaRepository);
    }

    // delete - ok
    @Test
    void givenValidParameters_whenDeleteMultimedia_thenExpectMultimediaDeletion()
    {
        // given
        final long mockMultimediaId = MOCK_ID1;
        final MRMultimedia multimediaToDelete = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        when(multimediaRepository.findById(anyLong())).thenReturn(Optional.of(multimediaToDelete));
        when(multimediaRepository.delete(any(MRMultimedia.class))).thenReturn(true);

        // when
        multimediaService.deleteMultimedia(mockMultimediaId);

        // then
        verify(multimediaRepository, times(1)).findById(mockMultimediaId);
        verify(multimediaRepository, times(1)).delete(any(MRMultimedia.class));
        verifyNoMoreInteractions(multimediaRepository);
    }

    // delete - nok (multimedia id not found)
    @Test
    void givenUnknownMultimediaId_whenMultimediaCar_thenThrowSpecificException()
    {
        // given
        final long mockMultimediaId = MOCK_UNKNOWN_ID;
        when(multimediaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when + then
        assertThrows(MarsRoverApiException.class, () -> multimediaService.deleteMultimedia(mockMultimediaId));
        verify(multimediaRepository, times(1)).findById(mockMultimediaId);
        verify(multimediaRepository, times(0)).delete(any(MRMultimedia.class));
        verifyNoMoreInteractions(multimediaRepository);
    }
}
