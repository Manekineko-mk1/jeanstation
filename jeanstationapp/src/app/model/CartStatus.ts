export class CartStatus {
    status: boolean;
    cartId: string;

    constructor(status: boolean, cartId: string) {
      this.status = status;
      this.cartId = cartId;
    };
}
