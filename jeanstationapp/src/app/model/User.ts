import { Address } from "./Address";

export class User {
    id?:string;
    username?:string;
    password?:string;
    userRole?:string;
    userStatus?:string;
    creationDate?:string;
    realName?:string;
    address?:Address;
    telephone?:string;
}