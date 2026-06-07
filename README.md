# 📍 Interactive Museum Guide

MVP веб-приложение для навигации внутри музея. Позволяет выбрать точку старта, уточнить текущее положение и построить пошаговый маршрут до нужной локации.

---

## 🚀 Технологии

- Next.js 14+
- React
- TypeScript
- Tailwind CSS
- LocalStorage (MVP-хранилище)

---

## 📦 Установка

```bash
git clone git@github.com:mazxal0/Interactive-MuseGuide.git
cd Interactive-MuseGuide/Frontend
```

### Запуск dev mode
```bash
npm install
▶️ Запуск проекта (development)
npm run dev
```

Открой в браузере:

http://localhost:3000


🏗️ Сборка проекта
```bash
npm run build
```
🚀 Запуск production
```bash
npm run start
```
🐳 Docker запуск
```bash
docker compose up --build
```


## Основной функционал
Выбор точки старта:
Вход
Касса
Этажи (0 / 1 / 2)
Уточнение местоположения внутри этажа:
Выставка
Туалет
Лестница
Выбор точки назначения
Построение маршрута (step-by-step инструкция)
Поддержка иконок:
- Выставка
- Туалет
- Лестница
- Вход
- Касса
- Архитектура проекта
/app/path     — построение маршрута
/app/admin    — добавление локаций (nodes)
/components   — UI компоненты и иконки

Данные на текущем этапе хранятся в:

localStorage (nodes)
## Модель данных (MVP)
Node (локация / проход)
type Node = {
  id: string;
  type: "location" | "passage";
  name: string;
  description: string;
  floor: "0" | "1" | "2";
};
## Планы развития
Backend API (nodes / edges graph)
Построение кратчайшего маршрута (graph algorithm)
Визуальная карта музея с линиями маршрута
Голосовая навигация для слабовидящих
Админ-панель с визуальным графом связей
Авторизация и роли (admin / user)

## Автор

Проект разработан как MVP системы indoor-навигации для музея.

Фокус:
- доступность
- простая навигация
- поддержка слабовидящих пользователей