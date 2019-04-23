package pt.caires.marsrover.multimedia.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE1;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TITLE2;
import static pt.caires.marsrover.multimedia.MultimediaTestHelper.MOCK_TYPE_PHOTO;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.caires.marsrover.multimedia.MultimediaTestHelper;
import pt.caires.marsrover.multimedia.domain.MRMultimedia;
import pt.caires.marsrover.multimedia.domain.MRMultimediaType;


class MultimediaRepositoryTest
{
    private MultimediaRepository multimediaRepository;

    @BeforeEach
    void setUp()
    {
        this.multimediaRepository = new DefaultMultimediaRepository();
    }

    // find all - empty repo
    @Test
    void givenEmptyMultimedia_whenFindAll_thenReturnEmptyCollection()
    {
        // given

        // when
        final List<MRMultimedia> result = multimediaRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(0));
    }

    // find all - existent multimedia
    @Test
    void givenExistentMultimedia_whenFindAll_thenReturnMultimediaCollection()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MRMultimediaType.VIDEO, MOCK_TITLE2);
        multimediaRepository.save(mockMultimedia1);
        multimediaRepository.save(mockMultimedia2);

        // when
        final List<MRMultimedia> result = multimediaRepository.findAll();

        // then
        assertNotNull(result);
        assertThat(result, hasSize(2));
        assertThat(result, containsInAnyOrder(mockMultimedia1, mockMultimedia2));
    }

    // find by - ok
    @Test
    void givenExistentMultimediaId_whenFindById_thenReturnExpectedMultimedia()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia insertedMultimedia = multimediaRepository.save(mockMultimedia1);
        final long mockTargetId = insertedMultimedia.getId();

        // when
        final Optional<MRMultimedia> result = multimediaRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.of(mockMultimedia1)));
    }

    // find by - not found
    @Test
    void givenUnknownMultimediaId_whenFindById_thenEmptyValue()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        multimediaRepository.save(mockMultimedia1);
        final long mockTargetId = MultimediaTestHelper.MOCK_UNKNOWN_ID;

        // when
        final Optional<MRMultimedia> result = multimediaRepository.findById(mockTargetId);

        // then
        assertThat(result, is(Optional.empty()));
    }

    // save (new item)
    @Test
    void givenMultimedia_whenSaveNewItem_thenReturnSavedMultimedia()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);

        // when
        final MRMultimedia result = multimediaRepository.save(mockMultimedia1);

        // then
        assertNotNull(result);
        assertThat(result, is(mockMultimedia1));
        assertThat(multimediaRepository.findAll(), hasSize(1));
    }

    // save (existent item)
    @Test
    void givenMultimedia_whenSaveExistentItem_thenReturnUpdatedMultimedia()
    {
        // given
        final MRMultimedia mockMultimedia = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        multimediaRepository.save(mockMultimedia);
        final MRMultimedia mockUpdatedMultimedia =
            MultimediaTestHelper.generateMultimedia(mockMultimedia.getId(), mockMultimedia.getType(), MOCK_TITLE2, mockMultimedia.getDateCreated());

        // when
        final MRMultimedia result = multimediaRepository.save(mockUpdatedMultimedia);

        // then
        assertNotNull(result);
        assertThat(result, is(mockUpdatedMultimedia));
        assertThat(multimediaRepository.findAll(), hasSize(1));
    }

    // delete - ok
    @Test
    void givenExistentMultimedia_whenDeleteItem_thenReturnTrue()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MRMultimediaType.VIDEO, MOCK_TITLE2);
        multimediaRepository.save(mockMultimedia1);
        multimediaRepository.save(mockMultimedia2);

        // when
        final boolean result = multimediaRepository.delete(mockMultimedia1);

        // then
        assertTrue(result);
        assertThat(multimediaRepository.findAll(), hasSize(1));
    }

    // delete - nok
    @Test
    void givenUnknownMultimedia_whenDeleteItem_thenReturnFalse()
    {
        // given
        final MRMultimedia mockMultimedia1 = MultimediaTestHelper.generateMultimedia(MOCK_TYPE_PHOTO, MOCK_TITLE1);
        final MRMultimedia mockMultimedia2 = MultimediaTestHelper.generateMultimedia(MRMultimediaType.VIDEO, MOCK_TITLE2);
        multimediaRepository.save(mockMultimedia1);
        multimediaRepository.save(mockMultimedia2);
        final MRMultimedia mockUnknownMultimedia = MultimediaTestHelper.generateMultimedia(MRMultimediaType.PHOTO, MOCK_TITLE2);

        // when
        final boolean result = multimediaRepository.delete(mockUnknownMultimedia);

        // then
        assertFalse(result);
        assertThat(multimediaRepository.findAll(), hasSize(2));
    }
}
