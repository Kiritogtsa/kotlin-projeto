export async function fetchAny<T>(url: string): Promise<T> {
    const res = await fetch(url, {
        credentials: "include",
    });
    return res.json();
}

