package hidalgo.brandon.a20180201_brandonhidalgo_nycschools;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model.BoroughPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;

import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class BoroughPresenterTest {
    @Mock
    private BoroughView view;

    private BoroughPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = spy(new BoroughPresenterImpl(view));
    }
}
