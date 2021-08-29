import { environment } from '../../environments/environment';

const cartServicePort = 1000;
const orderServicePort = 2000;
const productServicePort = 3000;
const userServicePort = 4000;

export const baseurl = environment.production ? 'http://api.placeholder.com' : 'http://localhost:';

