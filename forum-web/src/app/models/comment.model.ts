export interface Comment{
    id?: number;
    userName: string;
    content: String;
    date: Date | String;
    episodeId?: number;
}