import settings from "/@/utils/service/ajax/settings";

class Ajax {
    async getJson<T>(url: String): Promise<T> {
        const response = await fetch(`${settings}/${url}`, {
            "headers": {"accept": "application/json"},
            "method": "GET"
        });
        return await response.json();
    }
}

const ajax = new Ajax()

export default ajax