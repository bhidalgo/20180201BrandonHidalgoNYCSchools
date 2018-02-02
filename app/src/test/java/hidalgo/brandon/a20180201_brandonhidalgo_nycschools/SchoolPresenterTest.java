package hidalgo.brandon.a20180201_brandonhidalgo_nycschools;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model.SchoolPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SchoolPresenterTest {
    @Mock
    private SchoolView view;

    @Mock
    private SchoolPresenter presenter;

    @Before
    public void setUp() {
        presenter = spy(new SchoolPresenterImpl(view));
    }

    @Test
    public void testShowSchoolName(){
        String testSchoolName = "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES";

        presenter.showSchoolName(testSchoolName);

        verify(view).displaySchoolName(testSchoolName);
    }

    @Test
    public void testShowSchoolMatchScores() {
        String testMathScore = "404";

        presenter.showSchoolMathScores(testMathScore);

        verify(view).displaySchoolMathScores(testMathScore);
    }

    @Test
    public void testShowSchoolReadingScores() {
        String testReadingScore = "355";

        presenter.showSchoolReadingScores(testReadingScore);

        verify(view).displaySchoolReadingScores(testReadingScore);
    }

    @Test
    public void testShowSchoolDescription() {
        String testDescription = "Founded by the Asia Society, our school helps students acquire the knowledge and " +
                "skills needed to prepare for college and/or careers while in pursuit of knowledge of the histories, " +
                "economies, and languages of world regions. Teachers and other adults who make up the learning community forge " +
                "supportive relationships with students and parents while providing challenging and engaging learning experiences. " +
                "Our school partners with various community, arts, and business organizations to help students achieve success. " +
                "Our theme of international studies extends beyond the classroom, where students participate in ongoing Â‘Advisory Day OutÂ’ " +
                "excursions where the multiculturalism of NYC becomes the classroom.";

        presenter.showSchoolDescription(testDescription);

        verify(view).displaySchoolDescription(testDescription);
    }
}
