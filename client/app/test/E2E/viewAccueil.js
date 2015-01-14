describe('Accueil view' , function () {

	beforeEach(function(){ // Verify that page is OK before each test
		browser.get('http://localhost:4711/#/accueil');

		initH1 = browser.findElement(by.cssContainingText (".welcome", "Welcome"));
		initH2_1 = browser.findElement(by.cssContainingText (".qcmList", "QCM list"));
		initH2_2 = browser.findElement(by.cssContainingText (".createQCM", "Create QCM"));
		expect(initH1.isDisplayed()).toBe(true);
		expect(initH2_1.isDisplayed()).toBe(true);
		expect(initH2_2.isDisplayed()).toBe(true);

		initQCMCreationTitle = browser.findElement(by.model("newQCM.title"));
		initQCMCreationDescription = browser.findElement(by.model("newQCM.desc"));
		initQCMCreationUsers = browser.findElement(by.model("newQCM.users"));
		initButtonSubmit = browser.findElement(by.buttonText("Submit"));
		expect(initQCMCreationTitle.isDisplayed()).toBe(true);
		expect(initQCMCreationDescription.isDisplayed()).toBe(true);
		expect(initQCMCreationUsers.isDisplayed()).toBe(true);
		expect(initButtonSubmit.isDisplayed()).toBe(true);

		initQuestion = browser.findElement(by.model("newQ0.Question"));
		initAns1 = browser.findElement(by.model("newQ0.Answer1"));
		initAns2 = browser.findElement(by.model("newQ0.Answer2"));
		initAns3 = browser.findElement(by.model("newQ0.Answer3"));
		initButtonSave = browser.findElement(by.buttonText("Save"));
		expect(initQuestion.isDisplayed()).toBe(true);
		expect(initAns1.isDisplayed()).toBe(true);
		expect(initAns2.isDisplayed()).toBe(true);
		expect(initAns3.isDisplayed()).toBe(true);
		expect(initButtonSave.isDisplayed()).toBe(true);
	});

	it('look for route title', function(){ // Verify route redirection
		browser.get('http://localhost:4711/#/dqdqsdsFZRYFY3782');
		expect(browser.getTitle()).toEqual('QCM');
	});

	it('createQCM sample', function(){
		initQCMCreationTitle.sendKeys('QCMTitle');
		initQCMCreationDescription.sendKeys('QCMDescription');
		initQCMCreationUsers.sendKeys('1');
		expect(initQCMCreationTitle.getAttribute('value')).toBe('QCMTitle');
		expect(initQCMCreationDescription.getAttribute('value')).toBe('QCMDescription');
		expect(initQCMCreationUsers.getAttribute('value')).toBe('1');

		initQuestion.sendKeys("Question");
		initAns1.sendKeys("Answer1");
		initAns2.sendKeys("Answer2");
		initAns3.sendKeys("Answer3");
		expect(initQuestion.getAttribute('value')).toBe('Question');
		expect(initAns1.getAttribute('value')).toBe('Answer1');
		expect(initAns2.getAttribute('value')).toBe('Answer2');
		expect(initAns3.getAttribute('value')).toBe('Answer3');

		initButtonSave.click();

		initButtonSubmit.click();
	});
});