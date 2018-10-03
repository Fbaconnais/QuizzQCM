	package dal;

import java.util.List;

import bo.SectionTest;

public interface DAOSectionTest extends DAOGeneric<SectionTest> {
	public List<SectionTest> getListeSectionTestViaIdTest(int idTest) throws DALException;
	public SectionTest getSectionTestViaIdThemeAndIdTest(int idTest, int idTheme) throws DALException;
	
}
