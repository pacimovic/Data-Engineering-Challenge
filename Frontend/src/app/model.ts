export interface Transaction {
    event_id: number,
    event_timestamp: number,
    event_type: string,
    user_id: string,
    transaction_amount: number,
    transaction_currency: string
}

export interface Registration {
    event_id: number,
    event_timestamp: number,
    event_type: string,
    user_id: string,
    country: string,
    name: string,
    device_os: string,
    marketing_campaign: string
}

export interface Log {
    event_id: number,
    event_timestamp: number,
    event_type: string,
    user_id: string,
}