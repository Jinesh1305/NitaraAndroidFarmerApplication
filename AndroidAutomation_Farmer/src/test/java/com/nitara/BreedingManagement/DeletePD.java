package com.nitara.BreedingManagement;

import java.util.Map;
import org.testng.annotations.Test;

import com.nitara.APIFunctions.RegisterMilkingCattle;
import com.nitara.utils.DataProviderUtils;
import appCommonClasses.GenericBase;
import appCommonClasses.Helper_AppNavigation;
import appCommonClasses.HelperFunctions;

public class DeletePD extends GenericBase{

	@Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
	public void PD_ViewData(Map<String,String> data) throws Throwable {

		/** Register cattle */
		String url = prop.getProperty("APIbaseUrl");
		RegisterMilkingCattle reg = new RegisterMilkingCattle();
		String Tag = reg.registerMilkingOrDryCattle(url,"RegisterMilkingCattle_Inseminated");

		/** Go to cattle Profile page -> Select Breeding */
		farmerHomePage.waitForPageLoad();
		farmerHomePage.waitForPageLoad();
		new Helper_AppNavigation().goTo_addBreedingActivityScreenfromCattleProfile(Tag,"PD");

		/** Fill PD Form */
		addPDPage.enter_PDdate(generateRandomData.getPastDate(40-21));
		addPDPage.select_isCattlePregnant(data.get("isCattlePregnant"));
		addPDPage.assert_CattleTag(Tag);
		addPDPage.press_SaveButton();

		/** Assert cattletag in breeding successfully recorded page */
		breedingSuccessPage.cattletag_Assert(Tag);
		breedingSuccessPage.click_BackToBreedingTimeline();
		

		/** Delete the added insemination */
		breedingTimelinePage.press_deleteBtnRight();
		breedingTimelinePage.press_YesBtn();

		/** Assert Delete message */
		breedingTimelinePage.captureScreenshots("DeletePD");
		breedingTimelinePage.assertDeleteMsg("PD",generateRandomData.getPastDate(40-21));

	}
}
