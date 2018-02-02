package hidalgo.brandon.a20180201_brandonhidalgo_nycschools;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model.BoroughPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BoroughPresenterTest {
    @Mock
    private BoroughPresenter presenter;

    @Mock
    private BoroughView view;

    @Mock
    private List<Borough> list;

    @Before
    public void setUp() {
        presenter = new BoroughPresenterImpl(view);
    }

    @Test
    public void testGetBoroughs() {
        presenter.getBoroughs();

        verify(view).displayBoroughsList(ArgumentMatchers.<Borough>anyList());
    }
}
