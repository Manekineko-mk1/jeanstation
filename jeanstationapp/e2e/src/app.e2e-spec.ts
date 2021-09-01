import { browser, by, element, logging } from 'protractor';
import { AppPage } from './app.po';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', async () => {
    await page.navigateTo();
    expect(await page.getTitleText()).toEqual('JeanStation');
  });

  it('should load home component on base url', async () => {
    browser.get('/');
    element(by.linkText('Home')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl);
  });

  it('should load product component view on clicking Products', async () => {
    browser.get('/product');
    element(by.linkText('Products')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl+'product');
  });

  it('should load cart component view on clicking Cart', async () => {
    browser.get('/cart');
    element(by.linkText('Cart')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl+'cart');
  });

  it('should load login component view on clicking Login', async () => {
    browser.get('/login');
    element(by.linkText('Login')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl+'login');
  });

  it('should load registration component view on clicking Register', async () => {
    browser.get('/login');
    element(by.linkText('Register Now!')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl+'registration');
  });

  it('should load login component view on clicking Register', async () => {
    browser.get('/registration');
    element(by.linkText('Register')).click();
    expect(await browser.getCurrentUrl()).toEqual(browser.baseUrl+'login');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
