export interface Transaction {
    event_id: number,
    event_timestamp: number,
    event_type: string,
    user_id: string,
    transaction_amount: number,
    transaction_currency: string
}