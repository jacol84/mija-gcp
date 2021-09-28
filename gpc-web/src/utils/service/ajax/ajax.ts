import settings from "/@/utils/service/ajax/settings";

class Ajax {
    async getJson<T>(url: String): Promise<T> {
        const response = await fetch(`${settings}/${url}`, {
            "headers": {
                "Accept": "application/json",
                "content-type": "application/json; charset=UTF-8"
            },
            "method": "GET"
        });
        return await response.json();
    }

    async sendPost<T>(url: String, data: any): Promise<T> {
        const response = await fetch(`${settings}/${url}`, {
            "headers": {
                "Accept": "application/json",
                "content-type": "application/json; charset=UTF-8"
            },
            "method": "POST",
            body: JSON.stringify(data),
        });
        return await response.json();
    }

    async sendPut<T>(url: String, data: any): Promise<T> {
        const response = await fetch(`${settings}/${url}`, {
            "headers": {
                "Accept": "application/json",
                "content-type": "application/json; charset=UTF-8"
            },
            "method": "PUT",
            body: JSON.stringify(data),
        });
        return await response.json();
    }
}

const ajax = new Ajax()

export default ajax