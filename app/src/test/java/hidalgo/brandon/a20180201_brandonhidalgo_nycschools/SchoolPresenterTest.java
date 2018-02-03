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
}
