export const fetchme = async (url:string) => {
    const res = await fetch(url, {
        credentials: "include", // IMPORTANTE! cookies HttpOnly vêm daqui
    });
    if(!res.ok) return null
    return res.json()
}